package ru.otus;

import java.nio.file.Path;
import java.time.Instant;
import java.util.stream.Stream;

public class JvmInfoApp {

    public static void main(String[] args) {
        Path java = Path.of("bin", "java.exe");

        Stream<ProcessHandle> processes = ProcessHandle.allProcesses()
                .filter(ph -> ph.info().command().isPresent())
                .filter(ph -> Path.of(ph.info().command().get()).endsWith(java));

        processes.forEach(p -> {
            ProcessHandle.Info info = p.info();
            ProcessInfo processInfo = ProcessInfo.builder()
                    .pid(p.pid())
                    .parent(p.parent().map(ProcessHandle::pid).orElse(null))
                    .isAlive(p.isAlive())
                    .supportsTerm(p.supportsNormalTermination())
                    .startTime(info.startInstant().map(Instant::toString).orElse("-"))
                    .command(info.command().orElse("-"))
                    .build();

            System.out.println(processInfo.toString());
        });
    }

    private static class ProcessInfo {
        private Long pid;
        private Long parent;
        private boolean isAlive;
        private boolean supportsTerm;
        private String startTime;
        private String command;

        public static Builder builder() {
            return new ProcessInfo().new Builder();
        }

        public class Builder {

            private Builder() {}

            public Builder pid(Long pid) {
                ProcessInfo.this.pid = pid;
                return this;
            }

            public Builder parent(Long parent) {
                ProcessInfo.this.parent = parent;
                return this;
            }

            public Builder isAlive(boolean isAlive) {
                ProcessInfo.this.isAlive = isAlive;
                return this;
            }

            public Builder supportsTerm(boolean supportsTerm) {
                ProcessInfo.this.supportsTerm = supportsTerm;
                return this;
            }

            public Builder startTime(String startTime) {
                ProcessInfo.this.startTime = startTime;
                return this;
            }

            public Builder command(String command) {
                ProcessInfo.this.command = command;
                return this;
            }

            public ProcessInfo build() {
                return ProcessInfo.this;
            }

        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("pid: ")
                    .append(pid)
                    .append(", ")
                    .append("parent: ")
                    .append(parent)
                    .append(", ")
                    .append("isAlive: ")
                    .append(isAlive)
                    .append(", ")
                    .append("supportsNormTerm: ")
                    .append(supportsTerm)
                    .append(", ")
                    .append("startTime: ")
                    .append(startTime)
                    .append(", ")
                    .append("command: ")
                    .append(command)
                    .toString();
        }
    }
}
