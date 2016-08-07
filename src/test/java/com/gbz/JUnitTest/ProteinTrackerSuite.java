package com.gbz.JUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ HelloJunitTest.class, TrackingServiceTests.class })
public class ProteinTrackerSuite {

}
