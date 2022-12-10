config.middleware = config.middleware || [];
config.middleware.push('resource-loader');

config.set({
  client: {
    mocha: {
      timeout: 100000
    }
  },
  browserNoActivityTimeout: 100000,
  browserSocketTimeout: 100000,
  logLevel: config.LOG_DEBUG
})

function ResourceLoaderMiddleware() {
    const fs = require('fs');
    const PROJECT_PATH = process.env["PROJECT_PATH"]

    return function (request, response, next) {
        console.log("Trying to load resource " + decodeURI(request.originalUrl))
        try {
            const content = fs.readFileSync(PROJECT_PATH + '/build/processedResources/js/combined' + decodeURI(request.originalUrl));
            response.writeHead(200);
            response.end(content);
        } catch (ignored) {
            next();
        }
    }
}

config.plugins.push({
    'middleware:resource-loader': ['factory', ResourceLoaderMiddleware]
});