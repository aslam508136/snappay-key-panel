package v;

import a0.h;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f1953a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextDirectionHeuristic f1954b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1955c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1956d;

    public a(PrecomputedText.Params params) {
        this.f1953a = params.getTextPaint();
        this.f1954b = params.getTextDirection();
        this.f1955c = params.getBreakStrategy();
        this.f1956d = params.getHyphenationFrequency();
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0020  */
    /* JADX WARN: Code duplicated, block: B:18:0x0030  */
    /* JADX WARN: Code duplicated, block: B:19:0x0033  */
    public final boolean equals(Object obj) {
        TextPaint textPaint;
        float textScaleX;
        TextPaint textPaint2;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            if (this.f1955c == aVar.f1955c && this.f1956d == aVar.f1956d) {
                textPaint = this.f1953a;
                if (textPaint.getTextSize() != aVar.f1953a.getTextSize()) {
                    z2 = false;
                } else {
                    textScaleX = textPaint.getTextScaleX();
                    textPaint2 = aVar.f1953a;
                    if (textScaleX != textPaint2.getTextScaleX() && textPaint.getTextSkewX() == textPaint2.getTextSkewX() && textPaint.getLetterSpacing() == textPaint2.getLetterSpacing() && TextUtils.equals(textPaint.getFontFeatureSettings(), textPaint2.getFontFeatureSettings()) && textPaint.getFlags() == textPaint2.getFlags() && (i2 < 24 ? textPaint.getTextLocale().equals(textPaint2.getTextLocale()) : textPaint.getTextLocales().equals(textPaint2.getTextLocales())) && (textPaint.getTypeface() != null ? textPaint.getTypeface().equals(textPaint2.getTypeface()) : textPaint2.getTypeface() == null)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
            } else {
                z2 = false;
            }
        } else {
            textPaint = this.f1953a;
            if (textPaint.getTextSize() != aVar.f1953a.getTextSize()) {
                z2 = false;
            } else {
                textScaleX = textPaint.getTextScaleX();
                textPaint2 = aVar.f1953a;
                if (textScaleX != textPaint2.getTextScaleX()) {
                    z2 = false;
                } else {
                    z2 = true;
                }
            }
        }
        return z2 && this.f1954b == aVar.f1954b;
    }

    public final int hashCode() {
        int i2 = Build.VERSION.SDK_INT;
        int i3 = this.f1956d;
        int i4 = this.f1955c;
        TextDirectionHeuristic textDirectionHeuristic = this.f1954b;
        TextPaint textPaint = this.f1953a;
        return i2 >= 24 ? Objects.hash(Float.valueOf(textPaint.getTextSize()), Float.valueOf(textPaint.getTextScaleX()), Float.valueOf(textPaint.getTextSkewX()), Float.valueOf(textPaint.getLetterSpacing()), Integer.valueOf(textPaint.getFlags()), textPaint.getTextLocales(), textPaint.getTypeface(), Boolean.valueOf(textPaint.isElegantTextHeight()), textDirectionHeuristic, Integer.valueOf(i4), Integer.valueOf(i3)) : Objects.hash(Float.valueOf(textPaint.getTextSize()), Float.valueOf(textPaint.getTextScaleX()), Float.valueOf(textPaint.getTextSkewX()), Float.valueOf(textPaint.getLetterSpacing()), Integer.valueOf(textPaint.getFlags()), textPaint.getTextLocale(), textPaint.getTypeface(), Boolean.valueOf(textPaint.isElegantTextHeight()), textDirectionHeuristic, Integer.valueOf(i4), Integer.valueOf(i3));
    }

    public final String toString() {
        StringBuilder sb;
        Object textLocale;
        StringBuilder sb2 = new StringBuilder("{");
        StringBuilder sb3 = new StringBuilder("textSize=");
        TextPaint textPaint = this.f1953a;
        sb3.append(textPaint.getTextSize());
        sb2.append(sb3.toString());
        sb2.append(", textScaleX=" + textPaint.getTextScaleX());
        sb2.append(", textSkewX=" + textPaint.getTextSkewX());
        int i2 = Build.VERSION.SDK_INT;
        sb2.append(", letterSpacing=" + textPaint.getLetterSpacing());
        sb2.append(", elegantTextHeight=" + textPaint.isElegantTextHeight());
        if (i2 >= 24) {
            sb = new StringBuilder(", textLocale=");
            textLocale = textPaint.getTextLocales();
        } else {
            sb = new StringBuilder(", textLocale=");
            textLocale = textPaint.getTextLocale();
        }
        sb.append(textLocale);
        sb2.append(sb.toString());
        sb2.append(", typeface=" + textPaint.getTypeface());
        if (i2 >= 26) {
            sb2.append(", variationSettings=" + textPaint.getFontVariationSettings());
        }
        sb2.append(", textDir=" + this.f1954b);
        sb2.append(", breakStrategy=" + this.f1955c);
        sb2.append(", hyphenationFrequency=" + this.f1956d);
        sb2.append("}");
        return sb2.toString();
    }

    public a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 29) {
            h.h(textPaint).setBreakStrategy(i2).setHyphenationFrequency(i3).setTextDirection(textDirectionHeuristic).build();
        }
        this.f1953a = textPaint;
        this.f1954b = textDirectionHeuristic;
        this.f1955c = i2;
        this.f1956d = i3;
    }
}
