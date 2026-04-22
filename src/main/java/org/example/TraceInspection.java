package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class TraceInspection {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();

            //Start tracing
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)

            );

            Page page = context.newPage();
            page.navigate("https://3wassignment.netlify.app/");
            page.getByPlaceholder("name").click();
            page.getByPlaceholder("name").fill("Arshadars");
            page.getByPlaceholder("name").click();
            page.getByPlaceholder("name").fill("Arshad");
            page.getByPlaceholder("Social Media Handle").click();
            page.getByPlaceholder("Social Media Handle").fill("Arshad@286");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));

        }
    }
    }

