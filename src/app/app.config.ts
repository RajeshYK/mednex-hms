import { ApplicationConfig } from '@angular/core';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors } from '@angular/common/http';
import { TenantInterceptor } from './core/tenant.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
  { provide: HTTP_INTERCEPTORS, useClass: TenantInterceptor, multi: true }
]
};
