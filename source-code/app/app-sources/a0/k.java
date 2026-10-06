package a0;

import android.content.ClipData;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static x.c a(View view, x.c cVar) {
        CharSequence charSequenceCoerceToStyledText;
        CharSequence charSequenceCoerceToStyledText2;
        if (Log.isLoggable("ReceiveContent", 3)) {
            Log.d("ReceiveContent", "onReceive: " + cVar);
        }
        int i2 = cVar.f1973c;
        if (i2 == 2) {
            return cVar;
        }
        int i3 = 0;
        ClipData clipData = cVar.f1972b;
        if (i2 == 3) {
            TextView textView = (TextView) view;
            Context context = textView.getContext();
            int i4 = cVar.f1974d;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            while (i3 < clipData.getItemCount()) {
                ClipData.Item itemAt = clipData.getItemAt(i3);
                if ((i4 & 1) != 0) {
                    charSequenceCoerceToStyledText2 = itemAt.coerceToText(context);
                    if (charSequenceCoerceToStyledText2 instanceof Spanned) {
                        charSequenceCoerceToStyledText2 = charSequenceCoerceToStyledText2.toString();
                    }
                } else {
                    charSequenceCoerceToStyledText2 = itemAt.coerceToStyledText(context);
                }
                if (charSequenceCoerceToStyledText2 != null) {
                    spannableStringBuilder.append(charSequenceCoerceToStyledText2);
                }
                i3++;
            }
            b((Editable) textView.getText(), spannableStringBuilder);
            return null;
        }
        int i5 = cVar.f1974d;
        TextView textView2 = (TextView) view;
        Editable editable = (Editable) textView2.getText();
        Context context2 = textView2.getContext();
        boolean z2 = false;
        while (i3 < clipData.getItemCount()) {
            ClipData.Item itemAt2 = clipData.getItemAt(i3);
            if ((i5 & 1) != 0) {
                charSequenceCoerceToStyledText = itemAt2.coerceToText(context2);
                if (charSequenceCoerceToStyledText instanceof Spanned) {
                    charSequenceCoerceToStyledText = charSequenceCoerceToStyledText.toString();
                }
            } else {
                charSequenceCoerceToStyledText = itemAt2.coerceToStyledText(context2);
            }
            if (charSequenceCoerceToStyledText != null) {
                if (z2) {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), charSequenceCoerceToStyledText);
                } else {
                    b(editable, charSequenceCoerceToStyledText);
                    z2 = true;
                }
            }
            i3++;
        }
        return null;
    }

    public static void b(Editable editable, CharSequence charSequence) {
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        int iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
        int iMax2 = Math.max(0, Math.max(selectionStart, selectionEnd));
        Selection.setSelection(editable, iMax2);
        editable.replace(iMax, iMax2, charSequence);
    }
}
