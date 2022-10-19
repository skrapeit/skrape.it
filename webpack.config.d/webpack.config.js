const webpack = require("webpack")

config.resolve.fallback = {
    "url": require.resolve("url"),
    "assert": false,
    "path": false,
    "crypto": false,
    "http": false,
    "https": false,
    "zlib": false,
    "util": false,
    "os": false,
    "stream": require.resolve("stream-browserify"),
    "fs": false,
    "tls": false,
    "net": false,
    "child_process": false
};

config.plugins.push(new webpack.ProvidePlugin({
    process: "process/browser",
    Buffer: ["buffer","Buffer"],
    URL: "url"
}))

config.resolve.alias = {
    "testcontainers": false,
};