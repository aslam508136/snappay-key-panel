package j;

import android.app.Activity;
import android.text.Selection;
import android.text.Spannable;
import android.view.DragEvent;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class i0 {
    public static void a(DragEvent dragEvent, TextView textView, Activity activity) {
        activity.requestDragAndDropPermissions(dragEvent);
        int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
        textView.beginBatchEdit();
        try {
            Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
            x.u.c(textView, new x.c(new x.c(dragEvent.getClipData(), 3)));
        } finally {
            textView.endBatchEdit();
        }
    }
}
