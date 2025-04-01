import subprocess
import difflib

def get_tracked_files():
    """Gitで追跡されているファイルのリストを取得"""
    try:
        result = subprocess.run(
            ['git', 'ls-files'],
            capture_output=True,
            text=True,
            check=True
        )
        return result.stdout.splitlines()
    except subprocess.CalledProcessError as e:
        print(f"エラー: Gitコマンドの実行に失敗しました - {e}")
        return []

def is_line_ending_change_only(file_path):
    """ファイルの変更が行区切り文字の違いのみかを確認"""
    try:
        # 現在の作業ツリーのバージョンを取得
        working = subprocess.run(
            ['git', 'show', f':{file_path}'],
            capture_output=True,
            text=True,
            check=True
        ).stdout

        # インデックス（ステージングエリア）のバージョンを取得
        staged = subprocess.run(
            ['git', 'cat-file', 'blob', f'HEAD:{file_path}'],
            capture_output=True,
            text=True,
            check=True
        ).stdout

        # 行区切り文字を正規化して比較
        working_normalized = working.replace('\r\n', '\n').replace('\r', '\n')
        staged_normalized = staged.replace('\r\n', '\n').replace('\r', '\n')

        return working_normalized == staged_normalized

    except subprocess.CalledProcessError:
        return False

def stage_file(file_path):
    """ファイルをステージング領域に追加"""
    try:
        subprocess.run(
            ['git', 'add', file_path],
            check=True,
            capture_output=True
        )
        print(f"ステージングしました: {file_path}")
    except subprocess.CalledProcessError as e:
        print(f"ステージングに失敗しました {file_path}: {e}")

def main():
    # 追跡されているファイルを取得
    tracked_files = get_tracked_files()

    if not tracked_files:
        print("追跡されているファイルが見つかりませんでした")
        return

    # 変更があるファイルをチェック
    try:
        status = subprocess.run(
            ['git', 'status', '--porcelain'],
            capture_output=True,
            text=True,
            check=True
        ).stdout.splitlines()
    except subprocess.CalledProcessError as e:
        print(f"Gitステータスの取得に失敗しました: {e}")
        return

    # 変更された追跡ファイルの処理
    modified_files = [line.split()[1] for line in status if line.startswith(' M ')]

    for file_path in modified_files:
        if file_path in tracked_files:
            if is_line_ending_change_only(file_path):
                stage_file(file_path)
            else:
                print(f"スキップ: {file_path} - 行区切り文字以外の変更があります")

if __name__ == "__main__":
    main()