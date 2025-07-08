# Scopt Demo (SBT)

This is a Scala SBT project that demonstrates advanced use of [scopt](https://github.com/scopt/scopt) for command-line parsing.

## Prerequisites

- Java 17+ 
- Scala 2.13.x 
- SBT (Scala Build Tool) 1.11.3 or newer

## Commands

### Process

```bash
sbt "run process \
  -i input1.txt \
  -i input2.txt \
  --retries 5 \
  --verbose \
  --log-level debug \
  --include lib/core \
  --include lib/utils \
  --output result.out \
  --config settings.conf"
```

### Analyze

```bash
sbt "run analyze /var/logs --depth 3 --config settings.conf"
```

### Invalid examples

```bash
sbt "run process -i input.txt --retries -1"
sbt "run analyze /tmp --depth 0"
```
