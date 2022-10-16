config.middleware = config.middleware || [];
config.middleware.push('json')
config.middleware.push('testcontainer');

const express = require('express')

function JsonMiddleware() {
    return express.json()
}

function TestContainerMiddleware() {
    const fs = require('fs');
    const { GenericContainer, HostPortWaitStrategy } = require('testcontainers')
    const { inspect } = require('util')
    const PROJECT_PATH = process.env["PROJECT_PATH"]
    const ROOT_PROJECT_PATH = process.env["ROOT_PROJECT_PATH"]
    return async (request, response, next) => {
        console.log("Request in middleware")
        switch(decodeURI(request.originalUrl)) {
            case "/containers/create":
                if (request.method.toUpperCase() == "POST") {
                    console.log("Requesting container "+inspect(request.body))
                    const obj = request.body
                    //console.log("Instantiating "+obj.image)
                    var container = new GenericContainer(obj.image)
                    if (obj.mapResources) { //Hacky, hardcoded way to map files to the container
                        //console.log("Mapping Resources")
                        const testUtilsFilesPath = ROOT_PROJECT_PATH + "/test-utils/src/commonMain/resources"
                        const absolutePath = testUtilsFilesPath + obj.mapResources.realPath
                        container = container.withBindMount(absolutePath, obj.mapResources.containerPath, "ro")
                    }
                    if (obj.ports) {
                        //console.log("Mapping Ports "+inspect(obj.ports))
                        container = container.withExposedPorts(...obj.ports)
                    }
                    if (obj.command) {
                        container = container.withCmd(obj.command)
                    }
                    //Fire up the container
                    //console.log("Starting container")
                    container = await container.start()
                    //console.log("Fetching info")
                    //assemble info and return
                    const info = {
                        id: container.getId(),
                        ports: {},
                        address: container.getHost()
                    }
                    if (obj.ports) {
                        for (i in obj.ports) {
                            info.ports[obj.ports[i]] = container.getMappedPort(obj.ports[i])
                        }
                    }

                    const getMethods = (obj) => {
                      let properties = new Set()
                      let currentObj = obj
                      do {
                        Object.getOwnPropertyNames(currentObj).map(item => properties.add(item))
                      } while ((currentObj = Object.getPrototypeOf(currentObj)))
                      return [...properties.keys()].filter(item => typeof obj[item] === 'function')
                    }

                    //console.log("Info is "+inspect(info))
                    //console.log(inspect(response))
                    response.writeHead(200, { 'Content-Type': 'application/json' })
                    response.end(JSON.stringify(info))
                    break;
                }
            default:
                next();
        }
    }
}

config.plugins.push({
    'middleware:testcontainer': ['factory', TestContainerMiddleware],
    'middleware:json': ['factory', JsonMiddleware],
});