#Log4j Color

Enable log colorization for Log4j 1.2.x.

##Installation

#### Add dependency with your favorite build tool.

Example with *Maven*:

    <project>
        ...
  		<dependencies>
  			<dependency>
	    		<groupId>fr.jcgay.log4j</groupId>
      			<artifactId>log4j-color</artifactId>
      			<version>1.0</version>
      			<scope>runtime</scope>
    		</dependency>
  		</dependencies>
  		...
	</project>

##Configuration

Use appender `fr.jcgay.log4j.color.appender.AnsiConsoleAppender` under Windows to enable console ansi colorization (Using [Jansi](http://jansi.fusesource.org/)).

Use `fr.jcgay.log4j.color.layout.ColorEnhancedPatternLayout` to customize your layout with colors.  
Current recognize pattern:

* \#highlight() : Colorize text based on current log level (fatal/error in red, warning in yellow, info in bold).

Configuration example *log4j.xml*

    <?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

	<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    	<appender name="console" class="fr.jcgay.log4j.color.appender.AnsiConsoleAppender">
        	<param name="Target" value="System.out"/>
        	<layout class="fr.jcgay.log4j.color.layout.ColorEnhancedPatternLayout">
            	<param name="ConversionPattern" value="#highlight(%-5p) %c{1} - %m%n"/>
        	</layout>
    	</appender>
    	<root>
        	<priority value ="trace" />
        	<appender-ref ref="console" />
    	</root>
	</log4j:configuration>