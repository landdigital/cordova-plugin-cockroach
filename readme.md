# Cordova Cockroach
## Plugin for Apache Cordova that will reload your app if it crashes.
### Like a cockroach that just keeps coming back.

Cockroach currently closed the application with exit code 0 and does not pass
the exception to the default handler as this causes a popup to block the app
re-opening.
