package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CasCountTest {
    private static class ThreadCount extends Thread {
        private final CasCount count;

        private ThreadCount(final CasCount count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < 500; i++) {
                this.count.increment();
            }
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final CasCount count = new CasCount();
        Thread first = new CasCountTest.ThreadCount(count);
        Thread second = new CasCountTest.ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(1000));
    }
}