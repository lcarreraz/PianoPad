package cl.musicapplab.milmedios.pianoPad;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;


public class SoundManager {

    private static SoundManager instance = null;
    private Context context;

    SoundPool soundPool;
    protected int soundIds[] = new int[88];
    protected String notes[] = new String[88];
    protected boolean isLoaded = false;

    /**
     * @param context Context
     * @return SoundManager
     */
    public static SoundManager getInstance(Context context) {
        if (instance == null) {
            instance = new SoundManager(context);
        }

        return instance;
    }

    /**
     * Private construct
     *
     * @param context Context
     */
    private SoundManager(Context context) {
        this.context = context;
        initSoundPool();
        loadEffects();
        setNotesText();
    }


    private void setNotesText() {

        notes[0] = "c1";
        notes[1] = "c#1";
        notes[2] = "d1";
        notes[3] = "d#1";
        notes[4] = "e1";
        notes[5] = "f1";
        notes[6] = "f#1";
        notes[7] = "g1";
        notes[8] = "g#1";
        notes[9] = "a1";
        notes[10] = "a#1";
        notes[11] = "b1";

        notes[12] = "c2";
        notes[13] = "c#2";
        notes[14] = "d2";
        notes[15] = "d#2";
        notes[16] = "e2";
        notes[17] = "f2";
        notes[18] = "f#2";
        notes[19] = "g2";
        notes[20] = "g#2";
        notes[21] = "a2";
        notes[22] = "a#2";
        notes[23] = "b2";

        notes[24] = "c3";
        notes[25] = "c#3";
        notes[26] = "d3";
        notes[27] = "d#3";
        notes[28] = "e3";
        notes[29] = "f3";
        notes[30] = "f#3";
        notes[31] = "g3";
        notes[32] = "g#3";
        notes[33] = "a3";
        notes[34] = "a#3";
        notes[35] = "b3";

        notes[36] = "c4";
        notes[37] = "c#4";
        notes[38] = "d4";
        notes[39] = "d#4";
        notes[40] = "e4";
        notes[41] = "f4";
        notes[42] = "f#4";
        notes[43] = "g4";
        notes[44] = "g#4";
        notes[45] = "a4";
        notes[46] = "a#4";
        notes[47] = "b4";

        notes[48] = "c5";
        notes[49] = "c#5";
        notes[50] = "d5";
        notes[51] = "d#5";
        notes[52] = "e5";
        notes[53] = "f5";
        notes[54] = "f#5";
        notes[55] = "g5";
        notes[56] = "g#5";
        notes[57] = "a5";
        notes[58] = "a#5";
        notes[59] = "b5";


        notes[60] = "c6";
        notes[61] = "c#6";
        notes[62] = "d6";
        notes[63] = "d#6";
        notes[64] = "e6";
        notes[65] = "f6";
        notes[66] = "f#6";
        notes[67] = "g6";
        notes[68] = "g#6";
        notes[69] = "a6";
        notes[70] = "a#6";
        notes[71] = "b6";

        notes[72] = "c7";
        notes[73] = "c#7";
        notes[74] = "d7";
        notes[75] = "d#7";
        notes[76] = "e7";
        notes[77] = "f7";
        notes[78] = "f#7";
        notes[79] = "g7";
        notes[80] = "g#7";
        notes[81] = "a7";
        notes[82] = "a#7";
        notes[83] = "b7";

    }


    /**
     * Creates soundPool
     */
    private void initSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = createNewSoundPool();
        } else {
            soundPool = createOldSoundPool();
        }
    }

    /**
     * Creates soundPoll for lollipop and higher
     *
     * @return SoundPoll
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected SoundPool createNewSoundPool() {

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        return new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(8).build();


    }

    /**
     * Creates soundPool for api low than lollipop
     *
     * @return SoundPoll
     */
    @SuppressWarnings("deprecation")
    protected SoundPool createOldSoundPool() {

        return new SoundPool(8, AudioManager.STREAM_MUSIC, 0);

    }

    /**
     * Load effects to soundPool
     */
    protected void loadEffects() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    soundIds[0] = soundPool.load(context, R.raw.note_c1, 1);
                    soundIds[1] = soundPool.load(context, R.raw.note_c_sharp_1, 1);
                    soundIds[2] = soundPool.load(context, R.raw.note_d1, 1);
                    soundIds[3] = soundPool.load(context, R.raw.note_d_sharp_1, 1);
                    soundIds[4] = soundPool.load(context, R.raw.note_e1, 1);
                    soundIds[5] = soundPool.load(context, R.raw.note_f1, 1);
                    soundIds[6] = soundPool.load(context, R.raw.note_f_sharp_1, 1);
                    soundIds[7] = soundPool.load(context, R.raw.note_g1, 1);
                    soundIds[8] = soundPool.load(context, R.raw.note_g_sharp_1, 1);
                    soundIds[9] = soundPool.load(context, R.raw.note_a1, 1);
                    soundIds[10] = soundPool.load(context, R.raw.note_a_sharp_1, 1);
                    soundIds[11] = soundPool.load(context, R.raw.note_b1, 1);

                    soundIds[12] = soundPool.load(context, R.raw.note_c2, 1);
                    soundIds[13] = soundPool.load(context, R.raw.note_c_sharp_2, 1);
                    soundIds[14] = soundPool.load(context, R.raw.note_d2, 1);
                    soundIds[15] = soundPool.load(context, R.raw.note_d_sharp_2, 1);
                    soundIds[16] = soundPool.load(context, R.raw.note_e2, 1);
                    soundIds[17] = soundPool.load(context, R.raw.note_f2, 1);
                    soundIds[18] = soundPool.load(context, R.raw.note_f_sharp_2, 1);
                    soundIds[19] = soundPool.load(context, R.raw.note_g2, 1);
                    soundIds[20] = soundPool.load(context, R.raw.note_g_sharp_2, 1);
                    soundIds[21] = soundPool.load(context, R.raw.note_a2, 1);
                    soundIds[22] = soundPool.load(context, R.raw.note_a_sharp_2, 1);
                    soundIds[23] = soundPool.load(context, R.raw.note_b2, 1);

                    soundIds[24] = soundPool.load(context, R.raw.note_c3, 1);
                    soundIds[25] = soundPool.load(context, R.raw.note_c_sharp_3, 1);
                    soundIds[26] = soundPool.load(context, R.raw.note_d3, 1);
                    soundIds[27] = soundPool.load(context, R.raw.note_d_sharp_3, 1);
                    soundIds[28] = soundPool.load(context, R.raw.note_e3, 1);
                    soundIds[29] = soundPool.load(context, R.raw.note_f3, 1);
                    soundIds[30] = soundPool.load(context, R.raw.note_f_sharp_3, 1);
                    soundIds[31] = soundPool.load(context, R.raw.note_g3, 1);
                    soundIds[32] = soundPool.load(context, R.raw.note_g_sharp_3, 1);
                    soundIds[33] = soundPool.load(context, R.raw.note_a3, 1);
                    soundIds[34] = soundPool.load(context, R.raw.note_a_sharp_3, 1);
                    soundIds[35] = soundPool.load(context, R.raw.note_b3, 1);

                    soundIds[36] = soundPool.load(context, R.raw.note_c4, 1);
                    soundIds[37] = soundPool.load(context, R.raw.note_c_sharp_4, 1);
                    soundIds[38] = soundPool.load(context, R.raw.note_d4, 1);
                    soundIds[39] = soundPool.load(context, R.raw.note_d_sharp_4, 1);
                    soundIds[40] = soundPool.load(context, R.raw.note_e4, 1);
                    soundIds[41] = soundPool.load(context, R.raw.note_f4, 1);
                    soundIds[42] = soundPool.load(context, R.raw.note_f_sharp_4, 1);
                    soundIds[43] = soundPool.load(context, R.raw.note_g4, 1);
                    soundIds[44] = soundPool.load(context, R.raw.note_g_sharp_4, 1);
                    soundIds[45] = soundPool.load(context, R.raw.note_a4, 1);
                    soundIds[46] = soundPool.load(context, R.raw.note_a_sharp_4, 1);
                    soundIds[47] = soundPool.load(context, R.raw.note_b4, 1);

                    soundIds[48] = soundPool.load(context, R.raw.note_c5, 1);
                    soundIds[49] = soundPool.load(context, R.raw.note_c_sharp_5, 1);
                    soundIds[50] = soundPool.load(context, R.raw.note_d5, 1);
                    soundIds[51] = soundPool.load(context, R.raw.note_d_sharp_5, 1);
                    soundIds[52] = soundPool.load(context, R.raw.note_e5, 1);
                    soundIds[53] = soundPool.load(context, R.raw.note_f5, 1);
                    soundIds[54] = soundPool.load(context, R.raw.note_f_sharp_5, 1);
                    soundIds[55] = soundPool.load(context, R.raw.note_g5, 1);
                    soundIds[56] = soundPool.load(context, R.raw.note_g_sharp_5, 1);
                    soundIds[57] = soundPool.load(context, R.raw.note_a5, 1);
                    soundIds[58] = soundPool.load(context, R.raw.note_a_sharp_5, 1);
                    soundIds[59] = soundPool.load(context, R.raw.note_b5, 1);


                    soundIds[60] = soundPool.load(context, R.raw.note_c6, 1);
                    soundIds[61] = soundPool.load(context, R.raw.note_c_sharp_6, 1);
                    soundIds[62] = soundPool.load(context, R.raw.note_d6, 1);
                    soundIds[63] = soundPool.load(context, R.raw.note_d_sharp_6, 1);
                    soundIds[64] = soundPool.load(context, R.raw.note_e6, 1);
                    soundIds[65] = soundPool.load(context, R.raw.note_f6, 1);
                    soundIds[66] = soundPool.load(context, R.raw.note_f_sharp_6, 1);
                    soundIds[67] = soundPool.load(context, R.raw.note_g6, 1);
                    soundIds[68] = soundPool.load(context, R.raw.note_g_sharp_6, 1);
                    soundIds[69] = soundPool.load(context, R.raw.note_a6, 1);
                    soundIds[70] = soundPool.load(context, R.raw.note_a_sharp_6, 1);
                    soundIds[71] = soundPool.load(context, R.raw.note_b6, 1);

                    soundIds[72] = soundPool.load(context, R.raw.note_c7, 1);
                    soundIds[73] = soundPool.load(context, R.raw.note_c_sharp_7, 1);
                    soundIds[74] = soundPool.load(context, R.raw.note_d7, 1);
                    soundIds[75] = soundPool.load(context, R.raw.note_d_sharp_7, 1);
                    soundIds[76] = soundPool.load(context, R.raw.note_e7, 1);
                    soundIds[77] = soundPool.load(context, R.raw.note_f7, 1);
                    soundIds[78] = soundPool.load(context, R.raw.note_f_sharp_7, 1);
                    soundIds[79] = soundPool.load(context, R.raw.note_g7, 1);
                    soundIds[80] = soundPool.load(context, R.raw.note_g_sharp_7, 1);
                    soundIds[81] = soundPool.load(context, R.raw.note_a7, 1);
                    soundIds[82] = soundPool.load(context, R.raw.note_a_sharp_7, 1);
                    soundIds[83] = soundPool.load(context, R.raw.note_b7, 1);


                    soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                            isLoaded = true;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * Play sound effect
     *
     * @param effect int
     */
    public void play(int effect) {
            try {
                soundPool.play(soundIds[effect], 1, 1, 1, 0, 1.0f);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    public String select_note(int effect) {
            return notes[effect];

    }
}