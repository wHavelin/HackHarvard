package com.example.researchbeast.myspectrum.events;

import com.example.researchbeast.myspectrum.R;

public class StartActivityEvent {
    public int resourceId;

    public StartActivityEvent(int resourceId) {
        switch (resourceId) {
            case R.drawable.ic_note_add_black_48dp:
                this.resourceId = 0;
                break;
            case R.drawable.ic_find_in_page_black_48dp:
                this.resourceId = 1;
                break;
            case R.drawable.ic_mail_black_48dp:
                this.resourceId = 2;
                break;
            case R.drawable.ic_drafts_black_48dp:
                this.resourceId = 3;
                break;
            case R.drawable.ic_computer_black_48dp:
                this.resourceId = 4;
                break;
        }

    }
}
