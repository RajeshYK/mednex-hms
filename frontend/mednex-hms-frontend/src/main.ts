import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideRouter } from '@angular/router';

import { AppComponent } from './app/app.component';
import { routes } from './app/app.routes';
import { tenantInterceptor } from './app/core/tenant.interceptor';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),   // ✅ REQUIRED
    provideHttpClient(
      withInterceptors([tenantInterceptor])
    ), provideAnimationsAsync()
  ]
}).catch(err => console.error(err));
