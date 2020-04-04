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
java -cp audio.jar:bin Demo Audio_Source/"name_source_file".wav Audio_Filtred/"name_filtred_file".wav
```

## Run Reverb

```sh
java -cp audio.jar:bin Demo Reverb Audio_Source/"name_source_file".wav Audio_Filtred/"name_filtred_file".wav
```
