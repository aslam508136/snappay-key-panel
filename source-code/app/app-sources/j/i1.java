package j;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.snapay.app.R;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class i1 extends ListView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f1251b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1252c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1253d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1254e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1255f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1256g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Field f1257h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h1 f1258i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1259j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f1260k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1261l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public a0.d f1262m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public androidx.activity.b f1263n;

    public i1(Context context, boolean z2) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.f1251b = new Rect();
        this.f1252c = 0;
        this.f1253d = 0;
        this.f1254e = 0;
        this.f1255f = 0;
        this.f1260k = z2;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f1257h = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public final int a(int i2, int i3) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (adapter == null) {
            return measuredHeight;
        }
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        View view = null;
        int i4 = 0;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = adapter.getItemViewType(i5);
            if (itemViewType != i4) {
                view = null;
                i4 = itemViewType;
            }
            view = adapter.getView(i5, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i6 = layoutParams.height;
            view.measure(i2, i6 > 0 ? View.MeasureSpec.makeMeasureSpec(i6, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i5 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i3) {
                return i3;
            }
        }
        return measuredHeight;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001e  */
    /* JADX WARN: Code duplicated, block: B:69:0x012a  */
    /* JADX WARN: Code duplicated, block: B:71:0x0140  */
    /* JADX WARN: Code duplicated, block: B:73:0x0145  */
    /* JADX WARN: Code duplicated, block: B:75:0x0149  */
    /* JADX WARN: Code duplicated, block: B:77:0x015b  */
    /* JADX WARN: Code duplicated, block: B:79:0x015f  */
    /* JADX WARN: Code duplicated, block: B:81:0x0163  */
    public final boolean b(MotionEvent motionEvent, int i2) {
        boolean z2;
        View childAt;
        View childAt2;
        a0.d dVar;
        int actionMasked = motionEvent.getActionMasked();
        boolean z3 = false;
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                z2 = true;
            } else if (actionMasked != 3) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 || z3) {
                this.f1261l = false;
                setPressed(false);
                drawableStateChanged();
                childAt2 = getChildAt(this.f1256g - getFirstVisiblePosition());
                if (childAt2 != null) {
                    childAt2.setPressed(false);
                }
            }
            if (z2) {
                if (this.f1262m == null) {
                    this.f1262m = new a0.d(this);
                }
                a0.d dVar2 = this.f1262m;
                boolean z4 = dVar2.f28p;
                dVar2.f28p = true;
                dVar2.d(this, motionEvent);
            } else {
                dVar = this.f1262m;
                if (dVar != null) {
                    if (dVar.f28p) {
                        dVar.e();
                    }
                    dVar.f28p = false;
                }
            }
            return z2;
        }
        z2 = false;
        int iFindPointerIndex = motionEvent.findPointerIndex(i2);
        if (iFindPointerIndex < 0) {
            z2 = false;
        } else {
            int x2 = (int) motionEvent.getX(iFindPointerIndex);
            int y2 = (int) motionEvent.getY(iFindPointerIndex);
            int iPointToPosition = pointToPosition(x2, y2);
            if (iPointToPosition == -1) {
                z3 = true;
            } else {
                View childAt3 = getChildAt(iPointToPosition - getFirstVisiblePosition());
                float f2 = x2;
                float f3 = y2;
                this.f1261l = true;
                drawableHotspotChanged(f2, f3);
                if (!isPressed()) {
                    setPressed(true);
                }
                layoutChildren();
                int i3 = this.f1256g;
                if (i3 != -1 && (childAt = getChildAt(i3 - getFirstVisiblePosition())) != null && childAt != childAt3 && childAt.isPressed()) {
                    childAt.setPressed(false);
                }
                this.f1256g = iPointToPosition;
                childAt3.drawableHotspotChanged(f2 - childAt3.getLeft(), f3 - childAt3.getTop());
                if (!childAt3.isPressed()) {
                    childAt3.setPressed(true);
                }
                Drawable selector = getSelector();
                boolean z5 = (selector == null || iPointToPosition == -1) ? false : true;
                if (z5) {
                    selector.setVisible(false, false);
                }
                Field field = this.f1257h;
                int left = childAt3.getLeft();
                int top = childAt3.getTop();
                int right = childAt3.getRight();
                int bottom = childAt3.getBottom();
                Rect rect = this.f1251b;
                rect.set(left, top, right, bottom);
                rect.left -= this.f1252c;
                rect.top -= this.f1253d;
                rect.right += this.f1254e;
                rect.bottom += this.f1255f;
                try {
                    boolean z6 = field.getBoolean(this);
                    if (childAt3.isEnabled() != z6) {
                        field.set(this, Boolean.valueOf(!z6));
                        if (iPointToPosition != -1) {
                            refreshDrawableState();
                        }
                    }
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
                if (z5) {
                    float fExactCenterX = rect.exactCenterX();
                    float fExactCenterY = rect.exactCenterY();
                    selector.setVisible(getVisibility() == 0, false);
                    selector.setHotspot(fExactCenterX, fExactCenterY);
                }
                Drawable selector2 = getSelector();
                if (selector2 != null && iPointToPosition != -1) {
                    selector2.setHotspot(f2, f3);
                }
                h1 h1Var = this.f1258i;
                if (h1Var != null) {
                    h1Var.f1248c = false;
                }
                refreshDrawableState();
                if (actionMasked == 1) {
                    performItemClick(childAt3, iPointToPosition, getItemIdAtPosition(iPointToPosition));
                }
                z2 = true;
                z3 = false;
            }
        }
        if (z2) {
            this.f1261l = false;
            setPressed(false);
            drawableStateChanged();
            childAt2 = getChildAt(this.f1256g - getFirstVisiblePosition());
            if (childAt2 != null) {
                childAt2.setPressed(false);
            }
        } else {
            this.f1261l = false;
            setPressed(false);
            drawableStateChanged();
            childAt2 = getChildAt(this.f1256g - getFirstVisiblePosition());
            if (childAt2 != null) {
                childAt2.setPressed(false);
            }
        }
        if (z2) {
            if (this.f1262m == null) {
                this.f1262m = new a0.d(this);
            }
            a0.d dVar3 = this.f1262m;
            boolean z7 = dVar3.f28p;
            dVar3.f28p = true;
            dVar3.d(this, motionEvent);
        } else {
            dVar = this.f1262m;
            if (dVar != null) {
                if (dVar.f28p) {
                    dVar.e();
                }
                dVar.f28p = false;
            }
        }
        return z2;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.f1251b;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.f1263n != null) {
            return;
        }
        super.drawableStateChanged();
        h1 h1Var = this.f1258i;
        if (h1Var != null) {
            h1Var.f1248c = true;
        }
        Drawable selector = getSelector();
        if (selector != null && this.f1261l && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean hasFocus() {
        return this.f1260k || super.hasFocus();
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        return this.f1260k || super.hasWindowFocus();
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return this.f1260k || super.isFocused();
    }

    @Override // android.view.View
    public final boolean isInTouchMode() {
        return (this.f1260k && this.f1259j) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.f1263n = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f1263n == null) {
            androidx.activity.b bVar = new androidx.activity.b(this, 2);
            this.f1263n = bVar;
            post(bVar);
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iPointToPosition != -1 && iPointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(iPointToPosition, childAt.getTop() - getTop());
                }
                Drawable selector = getSelector();
                if (selector != null && this.f1261l && isPressed()) {
                    selector.setState(getDrawableState());
                }
            }
        } else {
            setSelection(-1);
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f1256g = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        androidx.activity.b bVar = this.f1263n;
        if (bVar != null) {
            i1 i1Var = (i1) bVar.f50b;
            i1Var.f1263n = null;
            i1Var.removeCallbacks(bVar);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z2) {
        this.f1259j = z2;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        h1 h1Var = drawable != null ? new h1(drawable) : null;
        this.f1258i = h1Var;
        super.setSelector(h1Var);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1252c = rect.left;
        this.f1253d = rect.top;
        this.f1254e = rect.right;
        this.f1255f = rect.bottom;
    }
}
