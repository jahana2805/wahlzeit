/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.services.mailing;

import com.google.appengine.repackaged.com.google.protobuf.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.Session;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailServiceTest {

	EmailService emailService = null;
	EmailAddress validAddress = null;
	EmailAddress bccAddress = null;
	Session session =null;
	SmtpEmailService smtp = new SmtpEmailService();


	@Before
	public void setup() throws Exception {
		emailService = EmailServiceManager.getDefaultService();
		validAddress = EmailAddress.getFromString("test@test.de");
		bccAddress = EmailAddress.getFromString("test@test.de");
	}

	@Test
	public void testSendInvalidEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendValidEmail() {
		assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
	}
	@Test
	public void testSendInvalidBCC() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, bccAddress, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress, bccAddress, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, bccAddress, "hi", "       "));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}



	@Test
	public void testSendValidBCC() {
			try {
				assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, bccAddress, "hi", "test"));
			} catch (Exception ex) {
				Assert.fail("Silent mode does not allow exceptions");
			}
		}


    @Test(expected = MailingException.class)
    public void testInvalidEmailFrom() throws MailingException {
	    emailService.sendEmail(null, validAddress, "test", "test");
    }

    @Test(expected = MailingException.class)
    public void testInvalidEmailTo() throws MailingException {
        emailService.sendEmail(validAddress, null,"test", "test");
    }

	}
