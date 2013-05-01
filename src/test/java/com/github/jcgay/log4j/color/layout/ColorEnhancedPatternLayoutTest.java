package com.github.jcgay.log4j.color.layout;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.fusesource.jansi.Ansi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fusesource.jansi.Ansi.ansi;

@RunWith(DataProviderRunner.class)
public class ColorEnhancedPatternLayoutTest {

    private ColorEnhancedPatternLayout layout;

    @Before
    public void init() {
        layout = new ColorEnhancedPatternLayout();
    }

    @Test
    @UseDataProvider("log_level_error_and_fatal")
    public void should_colorize_message_in_red_when_log_level_is_error_or_fatal(Level level) throws Exception {

        LoggingEvent event = event(level, "message");

        layout.setConversionPattern("#highlight(%p) %m");
        String result = layout.format(event);

        assertThat(result)
                .contains(ansi().fg(Ansi.Color.RED).bold().a(level).reset().toString())
                .contains("message");
    }

    @DataProvider
    public static Object[][] log_level_error_and_fatal() {
        return new Object[][] {
                {Level.ERROR},
                {Level.FATAL}
        };
    }

    @Test
    public void should_colorize_message_in_yellow_when_log_level_is_warning() throws Exception {

        LoggingEvent event = event(Level.WARN, "message");

        layout.setConversionPattern("#highlight(%p) %m");
        String result = layout.format(event);

        assertThat(result)
                .contains(ansi().fg(Ansi.Color.YELLOW).bold().a(Level.WARN).reset().toString())
                .contains("message");
    }

    @Test
    public void should_print_bold_text_when_log_level_is_info() throws Exception {

        LoggingEvent event = event(Level.INFO, "message");

        layout.setConversionPattern("#highlight(%p) %m");
        String result = layout.format(event);

        assertThat(result)
                .contains(ansi().bold().a(Level.INFO).reset().toString())
                .contains("message");
    }

    private static LoggingEvent event(Level level, String message) {
        return new LoggingEvent(
                "",
                null,
                0,
                level,
                message,
                "",
                null,
                "",
                null,
                null
        );
    }
}
