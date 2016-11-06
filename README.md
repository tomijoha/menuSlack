# MenuSlack

MenuSlack is HTTP Web Service API which collects lunch menus from Vallila area in Helsinki.

Uses JAX-WS Java web service libraries and JSoup HTML parser libraries

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

HTTP API is used currently with SMS gateway so that the end user can ask todays´s menus via SMS.

TODO: Slack Integration so ask the menus via Slack

```
Tomacat7 Server
SMS gateway
TODO: SlackIntegration
```

### API docs

##SMS lunch API

Request from SMS gateway:

```
http://localhost/menuSlack/menutoday?sender=<A number>&recip=$<B number>&msg=Lounaat
```

Response to SMS gateway:

```
http://localhost:8080/send?from=<B number>&to=$<A number>&msg=<menus>&smart=0
```

Example from the phone:

##Slack lunch API

TODO


## Contributing

## Versioning

## Authors

* **Tomi Johansson** - *Initial work* - [tomijoha](https://github.com/tomijoha)
