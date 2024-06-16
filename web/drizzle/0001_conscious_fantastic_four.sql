ALTER TABLE "session" ALTER COLUMN "id" SET DATA TYPE varchar(1024);--> statement-breakpoint
ALTER TABLE "session" ALTER COLUMN "user_id" SET DATA TYPE varchar(1024);--> statement-breakpoint
ALTER TABLE "user" ALTER COLUMN "id" SET DATA TYPE varchar(1024);--> statement-breakpoint
ALTER TABLE "session" ADD COLUMN "access_token" text NOT NULL;--> statement-breakpoint
ALTER TABLE "session" ADD COLUMN "refresh_token" text NOT NULL;--> statement-breakpoint
ALTER TABLE "session" ADD COLUMN "id_token" text NOT NULL;--> statement-breakpoint
ALTER TABLE "user" ADD COLUMN "username" varchar(512) NOT NULL;--> statement-breakpoint
ALTER TABLE "user" ADD COLUMN "email" varchar(512) NOT NULL;