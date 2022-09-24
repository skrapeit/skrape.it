config.middleware = config.middleware || [];
config.middleware.push('resource-loader');

/*config.files = config.files || [];
config.files.push({
  pattern: PROJECT_PATH + "/build/processedResources/js/test/**",
  included: false,
  served: true
  })

config.proxies = {"/resources/": PROJECT_PATH + "/build/processedResources/js/test/__files/"}
*/

config.set({
  client: {
    mocha: {
      timeout: 2000
    }
  }
})

function ResourceLoaderMiddleware() {
    const fs = require('fs');
    const PROJECT_PATH = process.env["PROJECT_PATH"]

    return function (request, response, next) {
        console.log("Trying to load resource " + decodeURI(request.originalUrl))
        try {
            const content = fs.readFileSync(PROJECT_PATH + '/build/processedResources/js/test' + decodeURI(request.originalUrl));
            response.writeHead(200);
            response.end(content);
        } catch (ignored) {
            try {
                const content = fs.readFileSync(PROJECT_PATH + '/build/processedResources/js/main' + decodeURI(request.originalUrl));
                response.writeHead(200);
                response.end(content);
            } catch (ignored) {
                next();
            }
        }
    }
}

config.plugins.push({
    'middleware:resource-loader': ['factory', ResourceLoaderMiddleware]
});