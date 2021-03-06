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

package com.googlecode.alv.creator.util;

import com.googlecode.alv.logData.Item;
import com.googlecode.alv.logData.turn.SingleTurn;
import com.googlecode.alv.logData.turn.TurnInterval;
import com.googlecode.alv.logData.turn.TurnVersion;

/**
 * A helper class for Spookyraven powerleveling statistics.
 */
final public class SpookyravenPowerleveling {
    private int ballroomTurns;

    private int ballStatNoncom;

    private int zombieWaltzers;

    private int danceCards;

    private int galleryTurns;

    private int louvre;

    private int bathroomTurns;

    private int bathNoncom;

    public SpookyravenPowerleveling(final Iterable<TurnInterval> turns)
    {
        if (turns == null)
            throw new NullPointerException("The turns collection must not be null.");

        for (final TurnInterval ti : turns) {
            if (ti.getAreaName().equals("Haunted Ballroom")) {
                ballroomTurns += ti.getTotalTurns();
                for (final Item i : ti.getDroppedItems())
                    if (i.getName().equals("dance card"))
                        danceCards += i.getAmount();
                for (final SingleTurn st : ti.getTurns())
                    if (st.getTurnVersion() == TurnVersion.NONCOMBAT
                        && st.getEncounterName().equals("Curtains"))
                        ballStatNoncom++;
                    else if (st.getTurnVersion() == TurnVersion.COMBAT
                             && st.getEncounterName().equals("zombie waltzers"))
                        zombieWaltzers++;

            } else if (ti.getAreaName().equals("Haunted Gallery")) {
                galleryTurns += ti.getTotalTurns();
                for (final SingleTurn st : ti.getTurns())
                    if (st.getTurnVersion() == TurnVersion.NONCOMBAT
                        && st.getEncounterName().startsWith("Louvre It or Leave It"))
                        louvre++;

            } else if (ti.getAreaName().equals("Haunted Bathroom")) {
                bathroomTurns += ti.getTotalTurns();
                for (final SingleTurn st : ti.getTurns())
                    if (st.getTurnVersion() == TurnVersion.NONCOMBAT)
                        bathNoncom++;
            }
        }
    }

    public int getBallroomTurns() 
    {
        return ballroomTurns;
    }

    public int getBallroomStatNoncombats() 
    {
        return ballStatNoncom;
    }

    public int getZombieWaltzers() 
    {
        return zombieWaltzers;
    }

    public int getDanceCards() 
    {
        return danceCards;
    }

    public int getGalleryTurns() 
    {
        return galleryTurns;
    }

    public int getLouvres() 
    {
        return louvre;
    }

    public int getBathroomTurns() 
    {
        return bathroomTurns;
    }

    public int getBathroomNoncombats() 
    {
        return bathNoncom;
    }
}
