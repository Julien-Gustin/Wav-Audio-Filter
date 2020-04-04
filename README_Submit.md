# Reverbator has been built
## Files used :

- AllpassFilter.java
- Artificial_Reverbator.java
- LowpassFilter.java
- TwoAllpassFilter.java
- NestedFilter.java
- And obviously basic Filter and CompositeFilter.java

# Using audio.jar

## Compilation

```sh
javac -d bin -cp bin:audio.jar src/*.java
```
Or use MakeFile by just typing make

**N.B.:** if you are under Windows, the ":" must be replaced by ";".

## Run Echo

```sh
java -cp audio.jar:bin Demo "name_source_file".wav "name_filtred_file".wav
```

## Run Reverb

```sh
java -cp audio.jar:bin Demo Reverb "name_source_file".wav "name_filtred_file".wav
```
