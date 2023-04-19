
const handler = module.exports = {};

/**
 * Inform about environmental lighting conditions for a particular streetlight.
 * @param {object} options
 * @param {object} options.message
 * @param {integer} options.message.payload.id - Id of the streetlight.
 * @param {integer} options.message.payload.lumens - Light intensity measured in lumens.
 * @param {string} options.message.payload.sentAt - Date and time when the message was sent.
 */
handler.onLightMeasured = async ({message}) => {
  // Implement your business logic here...
};
