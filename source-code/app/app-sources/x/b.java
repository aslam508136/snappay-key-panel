package x;

import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.snapay.app.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final View.AccessibilityDelegate f1962c = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View.AccessibilityDelegate f1963a = f1962c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1964b = new a(this);

    public void a(View view, AccessibilityEvent accessibilityEvent) {
        this.f1963a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void b(View view, y.c cVar) {
        this.f1963a.onInitializeAccessibilityNodeInfo(view, cVar.f2030a);
    }

    public boolean c(View view, int i2, Bundle bundle) {
        WeakReference weakReference;
        boolean z2;
        List listEmptyList = (List) view.getTag(R.id.tag_accessibility_actions);
        if (listEmptyList == null) {
            listEmptyList = Collections.emptyList();
        }
        boolean z3 = false;
        for (int i3 = 0; i3 < listEmptyList.size() && ((AccessibilityNodeInfo.AccessibilityAction) ((y.b) listEmptyList.get(i3)).f2028a).getId() != i2; i3++) {
        }
        boolean zPerformAccessibilityAction = this.f1963a.performAccessibilityAction(view, i2, bundle);
        if (zPerformAccessibilityAction || i2 != R.id.accessibility_action_clickable_span) {
            return zPerformAccessibilityAction;
        }
        int i4 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray != null && (weakReference = (WeakReference) sparseArray.get(i4)) != null) {
            ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
            if (clickableSpan == null) {
                z2 = false;
                break;
            }
            CharSequence text = view.createAccessibilityNodeInfo().getText();
            ClickableSpan[] clickableSpanArr = text instanceof Spanned ? (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class) : null;
            int i5 = 0;
            while (true) {
                if (clickableSpanArr == null || i5 >= clickableSpanArr.length) {
                    z2 = false;
                    break;
                }
                if (clickableSpan.equals(clickableSpanArr[i5])) {
                    z2 = true;
                    break;
                }
                i5++;
            }
            if (z2) {
                clickableSpan.onClick(view);
                z3 = true;
            }
        }
        return z3;
    }
}
