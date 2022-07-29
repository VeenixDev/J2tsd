# J2tsd

J2tsd is a simple tool to generate TypeScript Definitions based on Java classes.  
The typical usecase would be if you want to connect a Java backend with a TypeScript frontend.

Download the jar and execute it, then there will open a window. Configure just as you like and generate.

## Requirements

The project is dependency free.  
But you will need Java 15 to execute the compiled program

## ComboBox

At the ComboBox you can choose between different emitters, the only difference is how they output your class to TypeScript.

| TYPE       | Descriptions                                                                                        |
|------------|-----------------------------------------------------------------------------------------------------|
| AUTODETECT | J2tsd automatically chooses the emitter for every file                                              |
| CLASS      | J2tsd will handle every file as a class                                                             |
| ENUM       | J2tsd will handle every file as an enum                                                             |
| INTERFACE  | J2tsd will handle every file as an enum                                                             |
| INTERFACE_AS_CLASS | J2tsd will autodetect for every file except for interfaces these will be handled as classes |
