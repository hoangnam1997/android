'user strict';

let router = require('express').Router();

// Application routes
router.use('/menu', require('./menu'));
router.use('/home', require('./home'));
router.use('/detail', require('./detail'));

router.use((req, res, next) => {
    const err = new Error('Not exist router');
    return next(err);
});

// Error handle
router.use((err, req, res, next) => {
    const _status = err.status || 'null';
    res.json({
        message: err.message,
        status: _status
    });
});

module.exports = router;

