const Router = require('hermesjs/lib/router');

const router = new Router();
const userSignedupHandler = require('../handlers/user-signedup');
module.exports = router;

router.use('user/signedup', async (message, next) => {
  try {

    await userSignedupHandler._receiveUserSignedUp({
      message
    });
    next();
  } catch (e) {
    next(e);
  }
});