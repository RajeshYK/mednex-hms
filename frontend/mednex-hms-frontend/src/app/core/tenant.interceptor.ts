import { HttpInterceptorFn } from '@angular/common/http';

export const tenantInterceptor: HttpInterceptorFn = (req, next) => {
  const tenant = localStorage.getItem('TENANT_ID');

  if (tenant) {
    req = req.clone({
      setHeaders: {
        'X-Tenant-ID': tenant
      }
    });
  }

  return next(req);
};
