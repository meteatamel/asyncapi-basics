# Email Service

Receives user signed up event and sends out an email

## Set up your template

1. Install dependencies
    ```sh
    npm i
    ```


## Import and start

```js
// output refers to the generated template folder
const { client } = require('../output'); // Modify require path if needed

client.init(); // starts the app
```

## API

Use the `client.register<OperationId>Middleware` method as a bridge between the user-written handlers and the generated code. This can be used to register middlewares for specific methods on specific channels.

To send messages use built in sent function:
```js
 client.app.send({my: "message"}, {}, 'topic/name');
```
