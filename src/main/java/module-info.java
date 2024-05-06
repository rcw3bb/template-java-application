module template.java.application {

    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.slf4j2.impl;

    requires org.apache.commons.cli;
    requires java.scripting;

    requires xyz.ronella.casual.trivial;
    requires xyz.ronella.logging.logger.plus;
}