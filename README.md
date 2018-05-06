# Port Royale 3 Logbook

TODO: briefly explain what this is.

## Build

Work in progress, this project is not ready yet.

What you can do now:

```bash
mvn clean package
kotlin web/target/logbook-web-0.0.1-SNAPSHOT.jar
```
(this compiles and starts the backend server on port 8080)

```bash
cd angular-gui
ng serve --proxy-config proxy.conf.json --open
```
(this compiles and starts the frontend server on port 4200, and opens a browser window)
