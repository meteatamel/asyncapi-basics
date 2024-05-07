const handler = module.exports = {};

const receiveUserSignedUpMiddlewares = [];

/**
 * Registers a middleware function for the receiveUserSignedUp operation to be executed during request processing.
 *
 * Middleware functions have access to options object that you can use to access the message content and other helper functions
 *
 * @param {function} middlewareFn - The middleware function to be registered.
 * @throws {TypeError} If middlewareFn is not a function.
 */
handler.registerReceiveUserSignedUpMiddleware = (middlewareFn) => {
  if (typeof middlewareFn !== 'function') {
    throw new TypeError('middlewareFn must be a function');
  }
  receiveUserSignedUpMiddlewares.push(middlewareFn);
}

/**
   * 
   *
   * @param {object} options
   * @param {object} options.message
  
  *
   * @param {string} options.message.headers.displayName - Name of the user
 * @param {string} options.message.headers.email - Email of the user
  */
handler._receiveUserSignedUp = async ({
  message
}) => {
  for (const middleware of receiveUserSignedUpMiddlewares) {
    await middleware(message);
  }
};