/* Copyright (c) 2008-2020, developers of the Ascension Log Visualizer
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.googlecode.alv.parser.lineParsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.alv.logData.LogDataHolder;
import com.googlecode.alv.logData.MPGain;
import com.googlecode.alv.parser.UsefulPatterns;

/**
 * A parser to recognise Mental A-cue-ity effect acquisition.
 * <p>
 * The format looks like this:
 * <p>
 * {@code You acquire an effect: Mental A-cue-ity (duration: 10 Adventures)}
 *
 *
 *
 */
public final class PoolMPBuffLineParser extends AbstractLineParser {
    private static final Pattern POOL_MP_BUFF_ACQUISITION = Pattern.compile("You acquire an effect:\\s*Mental A-cue-ity.*$");

    private final Matcher poolMatcher = POOL_MP_BUFF_ACQUISITION.matcher(UsefulPatterns.EMPTY_STRING);

    /**
     * @see AbstractLineParser#doParsing(String, LogDataHolder)
     */
    @Override
    protected void doParsing(
                             final String line, final LogDataHolder logData) {
        logData.getLastTurnSpent().addMPGain(new MPGain(100, 0, 0, 0, 0));
    }

    /**
     * @see AbstractLineParser#isCompatibleLine(String)
     */
    @Override
    protected boolean isCompatibleLine(
                                       final String line) {
        return line.startsWith(UsefulPatterns.ACQUIRE_EFFECT_STRING) &&
                poolMatcher.reset(line).matches();
    }
}
