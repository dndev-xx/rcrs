package com.otus.kfl.rcrs.exception

class UnknownRequestClass(clazz: Class<*>) : RuntimeException("Class $clazz cannot be mapped to ProcessingContext")