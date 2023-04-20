
const handler = module.exports = {};

/**
 * 
 * @param {object} options
 * @param {object} options.message
 * @param {string} options.message.payload.displayName - Name of the user
 * @param {string} options.message.payload.email - Email of the user
 */
handler.receiveUserSignedUp = async ({message}) => {
  // Implement your business logic here...
};
