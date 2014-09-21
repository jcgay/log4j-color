package fr.jcgay.log4j.color.appender;

import org.apache.log4j.ConsoleAppender;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class AnsiConsoleAppender extends ConsoleAppender {

    @Override
    public void activateOptions() {
        super.activateOptions();
        if (getFollow()) {
            if (target.equals(SYSTEM_ERR)) {
                setWriter(createWriter(new AnsiStream(AnsiConsole.err)));
            } else {
                setWriter(createWriter(new AnsiStream(AnsiConsole.out)));
            }
        } else {
            if (target.equals(SYSTEM_ERR)) {
                setWriter(createWriter(AnsiConsole.err));
            } else {
                setWriter(createWriter(AnsiConsole.out));
            }
        }
    }

    private static class AnsiStream extends OutputStream {

        private PrintStream output;

        public AnsiStream(PrintStream output) {
            this.output = output;
        }

        public void close() {
        }

        public void flush() {
            output.flush();
        }

        public void write(final byte[] b) throws IOException {
            output.write(b);
        }

        public void write(final byte[] b, final int off, final int len)
                throws IOException {
            output.write(b, off, len);
        }

        public void write(final int b) throws IOException {
            output.write(b);
        }
    }
}
