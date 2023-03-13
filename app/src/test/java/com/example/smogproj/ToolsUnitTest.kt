package com.example.smogproj

import com.example.smogproj.util.Tools.formatDateString
import com.example.smogproj.util.Tools.roundDouble
import org.junit.Assert
import org.junit.Test

class ToolsUnitTest {

    //rounding correct
    @Test
    fun rounding_isCorrect() {
        Assert.assertEquals("40,4", roundDouble(40.4000))
    }

    @Test
    fun rounding_isCorrect_zero() {
        Assert.assertEquals("0", roundDouble(0.0))
    }

    @Test
    fun rounding_isCorrect_negative_low_one_digit() {
        Assert.assertEquals("-1,1", roundDouble(-1.1))
    }

    @Test
    fun rounding_isCorrect_negative_low_three_digits() {
        Assert.assertEquals("-1,99", roundDouble(-1.999))
    }

    @Test
    fun rounding_isCorrect_positive_low_three_digits() {
        Assert.assertEquals("0,12", roundDouble(0.123))
    }

    @Test
    fun rounding_isCorrect_positive_medium_two_digits() {
        Assert.assertEquals("20,56", roundDouble(20.56))
    }

    @Test
    fun rounding_isCorrect_positive_high_three_digits() {
        Assert.assertEquals("123,12", roundDouble(123.123))
    }

    //rounding not correct

    @Test
    fun rounding_isNotCorrect_negative_low_one_digit() {
        Assert.assertNotEquals("-1", roundDouble(-1.1))
    }

    @Test
    fun rounding_isNotCorrect_negative_low_four_digits() {
        Assert.assertNotEquals("-1,1", roundDouble(-1.1234))
    }

    @Test
    fun rounding_isNotCorrect_negative_low_three_digits() {
        Assert.assertNotEquals("-1,3", roundDouble(-1.129))
    }

    @Test
    fun rounding_isNotCorrect_negative_low() {
        Assert.assertNotEquals("-1", roundDouble(-0.9))
    }

    @Test
    fun rounding_isNotCorrect_positive_low() {
        Assert.assertNotEquals("1", roundDouble(0.99))
    }

    @Test
    fun rounding_isNotCorrect_positive_low_three_digits() {
        Assert.assertNotEquals("1,8", roundDouble(1.678))
    }

    @Test
    fun rounding_isNotCorrect_positive_high_many_digits() {
        Assert.assertNotEquals("124", roundDouble(123.999999999))
    }

    @Test
    fun rounding_isNotCorrect_positive_medium_many_digits() {
        Assert.assertNotEquals("32,9", roundDouble(32.999999999))
    }

    //format date correct
    @Test
    fun formatDate_isCorrect() {
        Assert.assertEquals("2017-03-28", formatDateString("2017-03-28 11:00:00"))
    }

    @Test
    fun formatDate_isCorrect_old_date() {
        Assert.assertEquals("1978-12-31", formatDateString("1978-12-31 23:59:59"))
    }

    @Test
    fun formatDate_isCorrect_another_year() {
        Assert.assertEquals("2018-01-01", formatDateString("2017-12-31 24:00:00"))
    }

    @Test
    fun formatDate_isCorrect_another_month() {
        Assert.assertEquals("2023-03-01", formatDateString("2023-02-28 24:00:00"))
    }

    @Test
    fun formatDate_isCorrect_another_day() {
        Assert.assertEquals("2023-02-16", formatDateString("2023-02-15 24:00:00"))
    }


    //format date not correct

    @Test
    fun formatDate_isNotCorrect_exact_dateTime() {
        Assert.assertNotEquals("2017-11-28 11:59:59", formatDateString("2017-11-28 11:59:59"))
    }

    @Test
    fun formatDate_isNotCorrect_day_added() {
        Assert.assertNotEquals("2023-03-13", formatDateString("2023-03-13 24:00:00"))
    }

    @Test
    fun formatDate_isNotCorrect_no_day_added() {
        Assert.assertNotEquals("2023-06-15", formatDateString("2023-06-14 23:59:59"))
    }

    @Test
    fun formatDate_isNotCorrect_no_month_added() {
        Assert.assertNotEquals("2015-11-30", formatDateString("2015-11-30 24:00:00"))
    }

    @Test
    fun formatDate_isNotCorrect_no_year_added() {
        Assert.assertNotEquals("2022-12-31", formatDateString("2022-12-31 24:00:00"))
    }
}