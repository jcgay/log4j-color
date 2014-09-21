package fr.jcgay.log4j.color.layout;

import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.fusesource.jansi.Ansi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fusesource.jansi.Ansi.ansi;

public class ColorEnhancedPatternLayout extends EnhancedPatternLayout {

    private static final Pattern HIGHLIGHT = Pattern.compile("#highlight\\((.+)\\)");

    @Override
    public String format(LoggingEvent event) {
        String message = super.format(event);

        StringBuffer result = new StringBuffer(message.length());
        Matcher matcher = HIGHLIGHT.matcher(message);
        while (matcher.find()) {
            matcher.appendReplacement(result, colorize(matcher.group(1), event.getLevel()));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private String colorize(String message, Level level) {
        Ansi builder = ansi();
        if (level.equals(Level.FATAL) || level.equals(Level.ERROR)) {
            builder.fg(Ansi.Color.RED).bold();
        }
        if (level.equals(Level.WARN)) {
            builder.fg(Ansi.Color.YELLOW).bold();
        }
        if (level.equals(Level.INFO)) {
            builder.bold();
        }
        return builder.a(message).reset().toString();
    }
}
