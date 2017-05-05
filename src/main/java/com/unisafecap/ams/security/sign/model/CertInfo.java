package com.unisafecap.ams.security.sign.model;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class CertInfo
{
  private PrivateKey priKey;
  private X509Certificate[] cert;

  public PrivateKey getPriKey()
  {
    return this.priKey;
  }

  public X509Certificate[] getCert() {
    return this.cert;
  }

  public void setPriKey(PrivateKey priKey) {
    this.priKey = priKey;
  }

  public void setCert(X509Certificate[] cert) {
    this.cert = cert;
  }
}