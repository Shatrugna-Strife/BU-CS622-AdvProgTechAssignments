from selenium import webdriver
from selenium.webdriver.common.by import By 
import os
import time
from components.split import split_video_into_scenes
from selenium.webdriver.support.ui import WebDriverWait 

class Youtube:

    # condition to check whether all the downloads in chrome are over.
    def download_complete(self, driver):
        if not driver.current_url.startswith("chrome://downloads"):
            driver.get("chrome://downloads/")
        return driver.execute_script("""
            var items = document.querySelector('downloads-manager')
                .shadowRoot.getElementById('downloadsList').items;
            if (items.every(e => e.state === "COMPLETE"))
                return items.map(e => e.fileUrl || e.file_url);
            """)

    # function to get all the queried youtube video links
    def get_download_links(self,keyword):
        for x in os.listdir("./videos"):
            os.remove("./videos/"+x)

        path = os.getcwd()
        preference = {"download.default_directory":path}
        options = webdriver.ChromeOptions()
        options.add_experimental_option("prefs", preference)
        driver = webdriver.Chrome(chrome_options=options,executable_path='./chromedriver.exe')

        s = "https://www.youtube.com/results?search_query="+keyword
        driver.get(s)
        video_rows = driver.find_elements(by=By.XPATH, value='//*[@id="video-title"]')
        download_links = []

        for i in video_rows:
            if i.get_attribute('href') == None:
                continue
            download_links.append(i.get_attribute('href'))
        driver.close()
        return download_links

    # function to download youtube video and process it
    def download_youtube_video(self,link):
        for x in os.listdir():
            if x.endswith(".mp4"):
                os.remove(x)
        path = os.getcwd()
        preference = {"download.default_directory":path}
        options = webdriver.ChromeOptions()
        options.add_experimental_option("prefs", preference)
        driver = webdriver.Chrome(chrome_options=options,executable_path='./chromedriver.exe')

        driver.get("https://ssyoutube.com/en31/youtube-video-downloader")
        driver.find_element(By.ID,"id_url").send_keys(link)
        time.sleep(5)
        driver.find_element(By.CSS_SELECTOR,"button[type='submit']").click()
        time.sleep(5)
        driver.execute_script("""
            var element = document.querySelector(".autocomplete-block");
            if (element)
                element.parentNode.removeChild(element);
        """)
        time.sleep(5)
        driver.find_element(By.ID,"download-mp4-720-audio").click()
        time.sleep(5)
        paths = WebDriverWait(driver, 120,1).until(self.download_complete)
        print(paths)
        flag = 0
        while flag<200:
            for x in os.listdir():
                if x.endswith(".mp4"):
                    split_video_into_scenes(x)
                    if os.path.exists("./videos/"+x):
                        os.remove("./videos/"+x)
                    os.replace("./"+x, "./videos/"+x)
                    os.makedirs("videos", exist_ok=True)
                    count = 0
                    videos_name = [x]
                    for y in os.listdir():
                        if y.startswith(x[:-4]):
                            count+=1
                            videos_name.append(y)
                            if os.path.exists("./videos/"+y):
                                os.remove("./videos/"+y)
                            os.replace("./"+y, "./videos/"+y)
                    driver.quit()
                    return videos_name
            time.sleep(1)
        driver.quit()
        return videos_name
    
    