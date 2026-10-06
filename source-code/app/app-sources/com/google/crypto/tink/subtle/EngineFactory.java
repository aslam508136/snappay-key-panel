package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EngineWrapper;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes.dex */
public final class EngineFactory<T_WRAPPER extends EngineWrapper<JcePrimitiveT>, JcePrimitiveT> {
    private final Policy<JcePrimitiveT> policy;
    public static final EngineFactory<EngineWrapper.TCipher, Cipher> CIPHER = new EngineFactory<>(new EngineWrapper.TCipher());
    public static final EngineFactory<EngineWrapper.TMac, Mac> MAC = new EngineFactory<>(new EngineWrapper.TMac());
    public static final EngineFactory<EngineWrapper.TSignature, Signature> SIGNATURE = new EngineFactory<>(new EngineWrapper.TSignature());
    public static final EngineFactory<EngineWrapper.TMessageDigest, MessageDigest> MESSAGE_DIGEST = new EngineFactory<>(new EngineWrapper.TMessageDigest());
    public static final EngineFactory<EngineWrapper.TKeyAgreement, KeyAgreement> KEY_AGREEMENT = new EngineFactory<>(new EngineWrapper.TKeyAgreement());
    public static final EngineFactory<EngineWrapper.TKeyPairGenerator, KeyPairGenerator> KEY_PAIR_GENERATOR = new EngineFactory<>(new EngineWrapper.TKeyPairGenerator());
    public static final EngineFactory<EngineWrapper.TKeyFactory, KeyFactory> KEY_FACTORY = new EngineFactory<>(new EngineWrapper.TKeyFactory());

    public static class AndroidPolicy<JcePrimitiveT> implements Policy<JcePrimitiveT> {
        private final EngineWrapper<JcePrimitiveT> jceFactory;

        private AndroidPolicy(EngineWrapper<JcePrimitiveT> engineWrapper) {
            this.jceFactory = engineWrapper;
        }

        @Override // com.google.crypto.tink.subtle.EngineFactory.Policy
        public JcePrimitiveT getInstance(String str) {
            Iterator<Provider> it = EngineFactory.toProviderList("GmsCore_OpenSSL", "AndroidOpenSSL").iterator();
            Exception exc = null;
            while (it.hasNext()) {
                try {
                    return this.jceFactory.getInstance(str, it.next());
                } catch (Exception e2) {
                    if (exc == null) {
                        exc = e2;
                    }
                }
            }
            return this.jceFactory.getInstance(str, null);
        }

        @Override // com.google.crypto.tink.subtle.EngineFactory.Policy
        public JcePrimitiveT getInstance(String str, List<Provider> list) {
            return getInstance(str);
        }
    }

    public static class DefaultPolicy<JcePrimitiveT> implements Policy<JcePrimitiveT> {
        private final EngineWrapper<JcePrimitiveT> jceFactory;

        private DefaultPolicy(EngineWrapper<JcePrimitiveT> engineWrapper) {
            this.jceFactory = engineWrapper;
        }

        @Override // com.google.crypto.tink.subtle.EngineFactory.Policy
        public JcePrimitiveT getInstance(String str) {
            return this.jceFactory.getInstance(str, null);
        }

        @Override // com.google.crypto.tink.subtle.EngineFactory.Policy
        public JcePrimitiveT getInstance(String str, List<Provider> list) {
            Iterator<Provider> it = list.iterator();
            while (it.hasNext()) {
                try {
                    return this.jceFactory.getInstance(str, it.next());
                } catch (Exception unused) {
                }
            }
            return getInstance(str);
        }
    }

    public static class FipsPolicy<JcePrimitiveT> implements Policy<JcePrimitiveT> {
        private final EngineWrapper<JcePrimitiveT> jceFactory;

        private FipsPolicy(EngineWrapper<JcePrimitiveT> engineWrapper) {
            this.jceFactory = engineWrapper;
        }

        @Override // com.google.crypto.tink.subtle.EngineFactory.Policy
        public JcePrimitiveT getInstance(String str) throws GeneralSecurityException {
            Iterator<Provider> it = EngineFactory.toProviderList("GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt").iterator();
            Exception exc = null;
            while (it.hasNext()) {
                try {
                    return this.jceFactory.getInstance(str, it.next());
                } catch (Exception e2) {
                    if (exc == null) {
                        exc = e2;
                    }
                }
            }
            throw new GeneralSecurityException("No good Provider found.", exc);
        }

        @Override // com.google.crypto.tink.subtle.EngineFactory.Policy
        public JcePrimitiveT getInstance(String str, List<Provider> list) {
            return getInstance(str);
        }
    }

    public interface Policy<JcePrimitiveT> {
        JcePrimitiveT getInstance(String str);

        JcePrimitiveT getInstance(String str, List<Provider> list);
    }

    public EngineFactory(T_WRAPPER t_wrapper) {
        this.policy = TinkFipsUtil.useOnlyFips() ? new FipsPolicy<>(t_wrapper) : SubtleUtil.isAndroid() ? new AndroidPolicy<>(t_wrapper) : new DefaultPolicy<>(t_wrapper);
    }

    public static List<Provider> toProviderList(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            }
        }
        return arrayList;
    }

    public JcePrimitiveT getInstance(String str) {
        return this.policy.getInstance(str);
    }

    public JcePrimitiveT getInstance(String str, List<Provider> list) {
        return this.policy.getInstance(str, list);
    }
}
