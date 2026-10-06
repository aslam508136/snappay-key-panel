package j;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class o2 extends TouchDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f1342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f1343c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f1344d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1345e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1346f;

    public o2(Rect rect, Rect rect2, SearchView.SearchAutoComplete searchAutoComplete) {
        super(rect, searchAutoComplete);
        int scaledTouchSlop = ViewConfiguration.get(searchAutoComplete.getContext()).getScaledTouchSlop();
        this.f1345e = scaledTouchSlop;
        Rect rect3 = new Rect();
        this.f1342b = rect3;
        Rect rect4 = new Rect();
        this.f1344d = rect4;
        Rect rect5 = new Rect();
        this.f1343c = rect5;
        rect3.set(rect);
        rect4.set(rect);
        int i2 = -scaledTouchSlop;
        rect4.inset(i2, i2);
        rect5.set(rect2);
        this.f1341a = searchAutoComplete;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x003d  */
    @Override // android.view.TouchDelegate
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        float width;
        int height;
        boolean z3;
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        boolean z4 = true;
        if (action != 0) {
            if (action == 1 || action == 2) {
                z3 = this.f1346f;
                if (z3 && !this.f1344d.contains(x2, y2)) {
                    z4 = z3;
                    z2 = false;
                }
            } else if (action != 3) {
                z2 = true;
                z4 = false;
            } else {
                z3 = this.f1346f;
                this.f1346f = false;
            }
            z4 = z3;
            z2 = true;
        } else if (this.f1342b.contains(x2, y2)) {
            this.f1346f = true;
            z2 = true;
        } else {
            z2 = true;
            z4 = false;
        }
        if (!z4) {
            return false;
        }
        Rect rect = this.f1343c;
        View view = this.f1341a;
        if (!z2 || rect.contains(x2, y2)) {
            width = x2 - rect.left;
            height = y2 - rect.top;
        } else {
            width = view.getWidth() / 2;
            height = view.getHeight() / 2;
        }
        motionEvent.setLocation(width, height);
        return view.dispatchTouchEvent(motionEvent);
    }
}
