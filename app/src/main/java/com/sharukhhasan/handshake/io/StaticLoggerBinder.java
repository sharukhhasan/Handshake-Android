package com.sharukhhasan.handshake.io;

import org.slf4j.ILoggerFactory;

import io.underdark.util.nslogger.NSLoggerFactory;

public class StaticLoggerBinder
{
    /**
     * The unique instance of this class.
     */
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

    /**
     * Return the singleton of this class.
     *
     * @return the StaticLoggerBinder singleton
     */
    public static final StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    /**
     * Declare the version of the SLF4J API this implementation is compiled against.
     * The value of this field is usually modified with each release.
     */
    // to avoid constant folding by the compiler, this field must *not* be final
    public static String REQUESTED_API_VERSION = "1.7.12";  // !final

    private final ILoggerFactory loggerFactory;

    private StaticLoggerBinder()
    {
        loggerFactory = new NSLoggerFactory("underdark");
    }

    public ILoggerFactory getLoggerFactory()
    {
        return loggerFactory;
    }

    private String factoryClassStr = NSLoggerFactory.class.getName();

    public String getLoggerFactoryClassStr()
    {
        return factoryClassStr;
    }
}