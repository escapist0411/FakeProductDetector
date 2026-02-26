import os
from icrawler.builtin import BingImageCrawler

BASE_PATH = "../dataset"

categories_real = [
    "original iPhone product",
    "original Samsung Galaxy phone",
    "original Dell laptop",
    "original HP laptop",
    "original JBL headphones",
    "original Sony headphones",
    "original iPad",
    "original Samsung tablet"
]

categories_fake = [
    "first copy iPhone",
    "replica Samsung Galaxy phone",
    "fake Dell laptop",
    "duplicate HP laptop",
    "replica JBL headphones",
    "fake Sony headphones",
    "copy iPad tablet",
    "fake Samsung tablet"
]

def download_images(keywords, folder):
    crawler = BingImageCrawler(
        storage={'root_dir': folder},
        downloader_threads=4
    )

    for keyword in keywords:
        print(f"Downloading: {keyword}")
        crawler.crawl(
            keyword=keyword,
            max_num=200,
            overwrite=True
        )

os.makedirs(f"{BASE_PATH}/real", exist_ok=True)
os.makedirs(f"{BASE_PATH}/fake", exist_ok=True)

download_images(categories_real, f"{BASE_PATH}/real")
download_images(categories_fake, f"{BASE_PATH}/fake")

print("Dataset Download Complete!")