package it.skrape.fetcher

import java.net.InetSocketAddress
import java.net.Proxy

public data class ProxyBuilder(
        var type: Proxy.Type = Proxy.Type.HTTP,
        var host: String = "",
        var port: Int = 0
) {
    public fun toProxy(): Proxy {
        if (host.isBlank() || port == 0) {
            return Proxy.NO_PROXY
        }
        return Proxy(type, InetSocketAddress(host, port))
    }
}
