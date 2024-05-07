const Router = require('hermesjs/lib/router');
const {
  validateMessage
} = require('../../lib/message-validator');
const router = new Router();
const userSignedupHandler = require('../handlers/user-signedup');
module.exports = router;

router.use('user/signedup', async (message, next) => {
  try {
    await validateMessage(message.payload, 'user/signedup', 'userSignedUp', 'publish');
    await userSignedupHandler._receiveUserSignedUp({
      message
    });
    next();
  } catch (e) {
    next(e);
  }
});