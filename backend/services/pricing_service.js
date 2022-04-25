const model = require("./../models/bookingsModel");
const { HTTP_500 } = require("./../Utilities/http_utils");
const bookingModel = require("./../models/bookingsModel");

/**
 * ROOM
 * min_guests
 * weekend_surge
 * festival_surge
 */

class PricingService {
  static guest_charge(guestInfo) {
    const { total_guests, min_guests, guest_fee } = guestInfo;

    console.info("Guest info = ", guestInfo);

    return Math.min(0, (total_guests - min_guests) * guest_fee);
  }

  static weekend_surge(date, surge) {
    const nd = new Date(date);
    if (nd.getDay() === 0 || nd.getDay() === 6) {
      // console.log("Weekend surge = ", surge);
      return surge;
    }
    return 0;
  }

  static festival_surge(date, surge) {
    const FESTIVALS = {
      "12/31": 1,
      "1/17": 1,
      "2/21": 1,
      "5/30": 1,
      "6/20": 1,
      "7/4": 1,
      "9/5": 1,
      "10/10": 1,
      "11/11": 1,
      "11/24": 1,
      "12/26": 1,
    };

    const nd = new Date(date);
    const festival = `${nd.getMonth() / nd.getDate()}`;

    // console.log("Festival Surge = ", surge);
    if (festival in FESTIVALS) return surge;
    return 0;
  }
}
