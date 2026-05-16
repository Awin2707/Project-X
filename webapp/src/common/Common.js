export const generateFingerPrintID = () => {
    let users = {
        user :window.navigator,
        language: navigator.language,
        platform: navigator.platform,
        hardwareConcurrency: navigator.hardwareConcurrency,
        deviceMemory: navigator.deviceMemory,
        screenWidth: window.screen.width,
        screenHeight: window.screen.height,
        timezone: Intl.DateTimeFormat().resolvedOptions().timeZone
    };
    let data = JSON.stringify(users);
    return btoa(data);
}