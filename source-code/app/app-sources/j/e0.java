package j;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1229c = {R.attr.indeterminateDrawable, R.attr.progressDrawable};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ProgressBar f1230a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bitmap f1231b;

    public e0(ProgressBar progressBar) {
        this.f1230a = progressBar;
    }

    public void a(AttributeSet attributeSet, int i2) {
        ProgressBar progressBar = this.f1230a;
        m0.a aVarU = m0.a.u(progressBar.getContext(), attributeSet, f1229c, i2);
        Drawable drawableL = aVarU.l(0);
        if (drawableL != null) {
            if (drawableL instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawableL;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i3 = 0; i3 < numberOfFrames; i3++) {
                    Drawable drawableB = b(animationDrawable.getFrame(i3), true);
                    drawableB.setLevel(10000);
                    animationDrawable2.addFrame(drawableB, animationDrawable.getDuration(i3));
                }
                animationDrawable2.setLevel(10000);
                drawableL = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(drawableL);
        }
        Drawable drawableL2 = aVarU.l(1);
        if (drawableL2 != null) {
            progressBar.setProgressDrawable(b(drawableL2, false));
        }
        aVarU.w();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Drawable b(Drawable drawable, boolean z2) {
        if (drawable instanceof s.b) {
            s.c cVar = (s.c) ((s.b) drawable);
            Drawable drawable2 = cVar.f1920g;
            if (drawable2 != null) {
                cVar.b(b(drawable2, z2));
            }
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    int id = layerDrawable.getId(i2);
                    drawableArr[i2] = b(layerDrawable.getDrawable(i2), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i3 = 0; i3 < numberOfLayers; i3++) {
                    layerDrawable2.setId(i3, layerDrawable.getId(i3));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (this.f1231b == null) {
                    this.f1231b = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z2 ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }
}
