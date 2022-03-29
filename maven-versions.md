
Gestion de dependencias con Maven
=================================

## Migra de Junit4 a Junit5

Ver proyecto [RomansGoHome](https://github.com/dfleta/romans-go-home)

Incluye las dependencias de Junit 5 y Junit parametrized test en el POM de tu proyecto y elimina las dependencias a Junit 4

Necesitarás añadir en el `pom.xml` estos plugin para Junit 5:

```xml
        <!-- surefire necesario para Junit 5-->
        <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-surefire-plugin</artifactId>
              <version>3.0.0-M5</version>
        </plugin>

          <plugin>
              <artifactId>maven-jar-plugin</artifactId>
              <version>3.2.0</version>
              <configuration>
                <archive>
                  <manifest>
                      <addClasspath>true</addClasspath>
                      <mainClass>org.foobarspam.kataromannumerals.RomansGoHome</mainClass>
                  </manifest>
                  <manifestEntries>
                      <url>${project.url}</url>
                  </manifestEntries>
                </archive>
            </configuration>
        </plugin>
```


Migra los casos test a Junit 5. Para ello, cambia la sintaxis de los casos test tal y como se indica en la doc oficial de Junit 5:

https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4

Observa si los asserts se escriben de la misma manera.

### Casos test parametrizados

Usa los casos test parametrizados en el caso test de la expresión regular que encontra el símbolo romano `M` repetido de 1 a 3 veces. Estudia los ejemplos de este mecanismo aquí y fíjate cómo lo he resuelto en mi proyecto:

https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests

Para usar parameterized tests necesitas la dependencia `junit-jupiter-params artifact`.

-------------------------


## Gestión de dependencias y versiones de plugins con Maven

Ver proyecto [RomansGoHome](https://github.com/dfleta/romans-go-home)

### 1. Dependencias

Chequea las versiones de tus dependencias en tu proyecto:

```sh
$ mvn versions:display-dependency-updates

The following dependencies in Dependencies have newer versions:
[INFO]   junit:junit ......................................... 4.13.1 -> 4.13.2
[INFO]   org.assertj:assertj-core ............................. 3.6.2 -> 3.22.0
```

Las dependencias se pueden actualizar desde CLI. El manual aquí:

versions-maven-plugin/usage

https://www.mojohaus.org/versions-maven-plugin/usage.html

```sh
$ mvn versions:use-latest-releases

Major version changes allowed   <<<===

[INFO] Updated junit:junit:jar:4.13.1 to version 4.13.2
[INFO] Updated org.assertj:assertj-core:jar:3.6.2 to version 3.22.0


$ mvn versions:display-dependency-updates

artifact org.junit.jupiter:junit-jupiter-api: checking for updates from central
[INFO] artifact org.junit.jupiter:junit-jupiter-params: checking for updates from central
[INFO] artifact org.assertj:assertj-core: checking for updates from central

[INFO] No dependencies in Dependencies have newer versions.
```

### 2. PLUGINS

Chequea el estado de tu proyecto comprobando si hay nuevas versiones de los plugins:

https://www.mojohaus.org/versions-maven-plugin/examples/display-plugin-updates.html

`$ mvn versions:display-plugin-updates`

#### 2.1 Versión mínima de Maven

Lee los _warnings_ y los mensajes de error para averiguar si has especificado la version mínima de Maven.

Para indicar la versión mínima de maven hay que incluir este plugin:

https://maven.apache.org/enforcer/enforcer-rules/requireMavenVersion.html

Para eliminar este error:

```sh
[ERROR] Project requires an incorrect minimum version of Maven.   <<<<====
[ERROR] Update the pom.xml to contain maven-enforcer-plugin to
[ERROR] force the Maven version which is needed to build this project.
[ERROR] See https://maven.apache.org/enforcer/enforcer-rules/requireMavenVersion.html
[ERROR] Using the minimum version of Maven: 3.0
```

definimos:

```xml
<groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-enforcer-plugin</artifactId>
            <version>3.0.0</version>
            <executions>
              <execution>
                <id>enforce-maven</id>
                <goals>
                  <goal>enforce</goal>
                </goals>
                <configuration>
                  <rules>
                    <requireMavenVersion>
                      <version>3.2.5</version>    <<<<=====
                ...
```  

ya que los plugins instalados me informan de la versión mínima que necesito:

```sh
[INFO] Plugins require minimum Maven version of: 3.2.5

[INFO] No plugins require a newer version of Maven than specified by the pom.
```

#### 2.2 Versiones de los plugins de maven


El plugin _versions_ te avisa si has especificado o no las versiones de los plugins que estás usando y  qué versión estás usando actualmente. 

**¡¡La mejor práctica es especificar siempre las versiones de los puglins para así asegurarnos de que el build es reproducible en cualquier máquina!!**


 1. Añade en el pom.xml en la sección plugins, los plugin defaults que obtienes de aquí:

https://www.mojohaus.org/versions-maven-plugin/examples/display-plugin-updates.html

```xml
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- default lifecycle, jar packaging: see https://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        <!-- site lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.7.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
```
       

 2. Vuelve a comprobar por CLI las versiones de los plugins: 

```sh
$ mvn versions:display-dependency-updates

The following plugin updates are available:
[INFO]   maven-compiler-plugin ............................. 3.8.0 -> 3.10.1
[INFO]   maven-deploy-plugin ............................. 2.8.2 -> 3.0.0-M2
[INFO]   maven-install-plugin ............................ 2.5.2 -> 3.0.0-M1
[INFO]   maven-jar-plugin ................................... 3.2.0 -> 3.2.2
[INFO]   maven-project-info-reports-plugin .................. 3.0.0 -> 3.2.2
[INFO]   maven-resources-plugin ............................. 3.0.2 -> 3.2.0
[INFO]   maven-site-plugin ................................. 3.7.1 -> 3.11.0
[INFO] 
[INFO] All plugins have a version specified.   <<<<<<<========
[INFO] 
[INFO] Project requires minimum Maven version for build of: 3.2.5
[INFO] Plugins require minimum Maven version of: 3.1.1
[INFO] 
[INFO] No plugins require a newer version of Maven than specified by the pom.
```

**Repasa que la version mínima de Maven que has indicado en tu proyecto sea la que requieren los plugins.**

3. Errores

Si peta el superpom o alguna de las dependencias no puede resolverse y se ha quedado guardado el `jar` en la cache de Maven, puedes forzar a descargar las dependencias de nuevo desde los repos remotos con la opción `-U`:

`$ mvn clean install -DskipTests -U`

Notice that -U means to reload the jars from maven, that ignore the cache jars in your local file system.


----------------------------

### 2.3 Cómo especificar las versiones de las dependencias en Maven

Aquí la **sintaxis** de las versiones de maven explicadas:

https://docs.oracle.com/middleware/1212/core/MAVEN/maven_version.htm#MAVEN402


Cómo actualizar las versiones de las dependencias:

Este es el conjunto de goals para la gestión avanzada de dependencias: `use-latest-releases, use-latest-snapshots, use-latest-versions, use-next-releases, use-next-snapshots, and use-next-versions`.

explicadas aquí:

Advancing dependency versions

https://www.mojohaus.org/versions-maven-plugin/examples/advancing-dependency-versions.html

_to be continued..._