const Router = require('hermesjs/lib/router');
const {validateMessage} = require('../../lib/message-validator');
const router = new Router();
const userSignedupHandler = require('../handlers/user-signedup');
module.exports = router;



router.useOutbound('user/signedup', async (message, next) => {
  try {
    
    await validateMessage(message.payload,'user/signedup','userSignedUp','subscribe');
    await userSignedupHandler.publishUserSignedUp({message});
    next();
    
  } catch (e) {
    next(e);
  }
});
