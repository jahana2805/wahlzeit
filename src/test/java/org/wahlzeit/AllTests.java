package org.wahlzeit;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.TellFriendTest;
import org.wahlzeit.model.*;
import org.wahlzeit.services.ServicesTestSuite;
import org.wahlzeit.utils.UtilsTestSuite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        TellFriendTest.class,

        ModelTestSuite.class,

        ServicesTestSuite.class,

        UtilsTestSuite.class
})

        public class AllTests {
        }

